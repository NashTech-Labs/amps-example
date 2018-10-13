package com.example.amps

import com.crankuptheamps.client.HAClient

import scala.collection.JavaConverters._
import scala.util.Try

class AmpsHandler(hAClient: HAClient) {

  def publish(topicName: String, message: String) = {
    Try {
      println(s"HA Client Info : ${hAClient.getConnectionInfo}")
      hAClient.publish(topicName, message)
    }
  }

  def subscribe(topicName: String) = {
    Try {
      hAClient.subscribe(topicName).iterator().asScala
    }
  }
}
