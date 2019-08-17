package com.fdelosrios.logistics.containers.infrastructure.entities

import play.api.libs.json.{Format, Json}

/**
  * The current state held by the persistent entity.
  */
case class ContainerState(model: Option[String], load: Option[Load], status: String, timestamp: String)

object ContainerState {
  /**
    * Format for the hello state.
    *
    * Persisted entities get snapshotted every configured number of events. This
    * means the state gets stored to the database, so that when the entity gets
    * loaded, you don't need to replay all the events, just the ones since the
    * snapshot. Hence, a JSON format needs to be declared so that it can be
    * serialized and deserialized when storing to and from the database.
    */
  implicit val format: Format[ContainerState] = Json.format
}

case class Load(owner: String, description: String)

object Load {
  implicit val format: Format[Load] = Json.format
}

object ContainerStatus extends Enumeration {
  type ContainerStatus = Value
  val UNREGISTERED, REGISTERED, EMPTY, FULL, ON_SHIP = Value
}