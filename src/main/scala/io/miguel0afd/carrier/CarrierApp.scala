/*
 *
 *  * Licensed to STRATIO (C) under one or more contributor license agreements.
 *  * See the NOTICE file distributed with this work for additional information
 *  * regarding copyright ownership.  The STRATIO (C) licenses this file
 *  * to you under the Apache License, Version 2.0 (the
 *  * "License"); you may not use this file except in compliance
 *  * with the License.  You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied.  See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *
 *
 */

package io.miguel0afd.carrier

import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.{IgniteCache, IgniteCluster, Ignition, Ignite}

case class Fruit(name: String, origin: String)

object CarrierApp extends App {
  val config: IgniteConfiguration = new IgniteConfiguration
  val ignite: Ignite = Ignition.start(config)
  val cluster: IgniteCluster = ignite.cluster

  // Obtain instance of cache named "fruits".
  // Note that different caches may have different generics.
  val cache: IgniteCache[String, Fruit] = ignite.getOrCreateCache("fruits")
  val fruit: Fruit = Fruit("Durian", "Indonesia")
  cache.put(fruit.name, fruit)
  val result: Fruit = cache.get(fruit.name)
  println(result.name)
  ignite.close
}
