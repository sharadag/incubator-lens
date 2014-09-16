/*
 * #%L
 * Grill Server
 * %%
 * Copyright (C) 2014 Inmobi
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.inmobi.grill.server;

import org.apache.hadoop.hive.conf.HiveConf;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
@Test(alwaysRun=true, groups="unit-test")
public class TestGrillApplication {

  GrillApplication app;

  @BeforeTest
  public void setup() throws Exception {
    HiveConf conf = new HiveConf();
    conf.addResource(Thread.currentThread().getContextClassLoader().getResource("grillserver-default.xml"));
    conf.addResource(Thread.currentThread().getContextClassLoader().getResource("grill-site.xml"));
    GrillApplication.init(conf);
    app = new GrillApplication();
  }

  @Test
  public void testWSResourcesLoaded() throws InterruptedException {
    final Set<Class<?>> classes = app.getClasses();
    assertEquals(classes.size(),12);
    assertTrue(classes.contains(TestResource.class));
  }
}
