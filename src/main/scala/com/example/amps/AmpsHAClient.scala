package com.example.amps

import com.crankuptheamps.client.{DefaultServerChooser, HAClient}

class AmpsHAClient {

  def getConnection(clientName: String, serverIpAddress: List[String]) = {
    val ampsHAClient = HAClient.createMemoryBacked(clientName)
    val serverChooser = new DefaultServerChooser()
    serverIpAddress.map(ipAddress => serverChooser.add(ipAddress))
    ampsHAClient.setServerChooser(serverChooser)
    ampsHAClient.connectAndLogon()
    ampsHAClient
  }
}
