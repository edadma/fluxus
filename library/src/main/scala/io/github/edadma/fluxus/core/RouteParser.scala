package io.github.edadma.fluxus.core

case class RouteMatch(
    pathParams: Map[String, String],
    queryParams: Map[String, Seq[String]],
)

object RouteParser {
  def parse(pattern: String, url: String): Option[RouteMatch] = {
    // Split URL into path and query parts
    val (path, query) = url.split("\\?", 2) match {
      case Array(p, q) => (p, Some(q))
      case Array(p)    => (p, None)
    }

    // Match path first - if no match, entire route doesn't match
    matchPath(pattern, path).map { pathParams =>
      // If path matches, parse query parameters (if any)
      val queryParams = query.filter(_.nonEmpty)
        .map(parseQuery)
        .getOrElse(Map.empty)

      RouteMatch(pathParams, queryParams)
    }
  }

  private def matchPath(pattern: String, path: String): Option[Map[String, String]] = {
    def normalize(p: String) = p.stripPrefix("/")
      .stripSuffix("/")
      .split("/")
      .filter(_.nonEmpty)

    val patternSegs = normalize(pattern)
    val pathSegs    = normalize(path)

    if (patternSegs.length != pathSegs.length) None
    else {
      // Use collectFirst to break early on mismatch
      val params = patternSegs.zip(pathSegs).foldLeft(Option(Map.empty[String, String])) {
        case (None, _) => None // Previous mismatch
        case (Some(acc), (patternSeg, pathSeg)) =>
          if (patternSeg.startsWith(":")) {
            Some(acc + (patternSeg.drop(1) -> pathSeg))
          } else if (patternSeg == pathSeg) {
            Some(acc)
          } else {
            None // Mismatch
          }
      }
      params
    }
  }

  private def parseQuery(query: String): Map[String, Seq[String]] = {
    if (query.isEmpty) Map.empty
    else {
      // Split on & and parse each parameter
      query.split("&")
        .filter(_.nonEmpty)
        .map { param =>
          param.split("=", 2) match {
            case Array(key, value) =>
              (decodeURIComponent(key), decodeURIComponent(value))
            case Array(key) =>
              (decodeURIComponent(key), "") // Handle valueless parameters
          }
        }
        .groupBy(_._1) // Group by parameter name
        .map { case (k, v) => k -> v.map(_._2).toSeq } // Extract values
    }
  }

  // Helper to decode URL components
  private def decodeURIComponent(s: String): String =
    java.net.URLDecoder.decode(s, "UTF-8")
}
