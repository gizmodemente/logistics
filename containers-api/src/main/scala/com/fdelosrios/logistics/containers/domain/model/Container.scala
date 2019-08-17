package com.fdelosrios.logistics.containers.domain.model

import play.api.libs.json.{Format, Json}

case class Container(containerId: String, status: String, location: String)

object Container {
  implicit val format: Format[Container] = Json.format[Container]
}

case class Load(id: String, company: String)

object Load {
  implicit val format: Format[Load] = Json.format[Load]
}