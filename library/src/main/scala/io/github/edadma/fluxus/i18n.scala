package io.github.edadma.fluxus

import com.raquo.airstream.state.Var
import io.github.edadma.yaml._

object I18n {
  // The current language signal
  val currentLanguage = Var[String]("en")

  // Translation data storage - flat maps with dot notation keys
  private var translationData: Map[String, Map[String, String]] = Map.empty

  // Signal derived from current language that provides the current translations
  val translations = currentLanguage.signal.map(lang =>
    translationData.getOrElse(lang, Map.empty),
  )

  // Load translations from YAML string
  def loadTranslation(lang: String, yamlString: String): Unit = {
    try {
      val yamlNode  = readFromString(yamlString)
      val dataMap   = yamlNode.construct.asInstanceOf[Map[String, Any]]
      val flattened = flattenTranslations(dataMap)

      translationData = translationData + (lang -> flattened)
      logger.debug(
        "Loaded translations",
        category = "I18n",
        Map(
          "language" -> lang,
          "keyCount" -> flattened.size.toString,
        ),
      )
    } catch {
      case e: Throwable =>
        logger.error(
          "Failed to load translations",
          category = "I18n",
          Map(
            "language" -> lang,
            "error"    -> e.toString,
          ),
        )
    }
  }

  // Flatten nested maps to dot notation
  private def flattenTranslations(obj: Map[String, Any], prefix: String = ""): Map[String, String] = {
    obj.flatMap {
      case (key, value: String) => Map(s"$prefix$key" -> value)
      case (key, value: Map[_, _]) =>
        flattenTranslations(value.asInstanceOf[Map[String, Any]], s"$prefix$key.")
      case (key, value) =>
        // Convert numbers, booleans etc. to strings
        Map(s"$prefix$key" -> value.toString)
    }
  }

  // Helper to change language
  def setLanguage(lang: String): Unit = {
    if (translationData.contains(lang)) {
      logger.debug(
        "Changing language",
        category = "I18n",
        Map(
          "from" -> currentLanguage.now(),
          "to"   -> lang,
        ),
      )
      currentLanguage.set(lang)
    } else {
      logger.warn(
        "Language not available",
        category = "I18n",
        Map(
          "requestedLanguage"  -> lang,
          "currentLanguage"    -> currentLanguage.now(),
          "availableLanguages" -> translationData.keys.mkString(", "),
        ),
      )
    }
  }
}
