package com.example.utils

import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

object Constants {

  val config = ConfigFactory.load()
  val TOPIC_NAME = config.getString("amps.topic")
  val SOW_TOPIC_NAME = config.getString("amps.sow-topic")
  val SERVER_IPS = config.getStringList("amps.servers").asScala.toList
  val CLIENT_NAME = config.getString("amps_client.client_name")
  val MESSAGE = "{ \"message\" : \"Hello, world!\" }"
  val SOW_MESSAGE = "{ \"id\" : \"1234\" , \"message\" : \"Hello, world!\" }"
}
