/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.systemtest.olm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Collections;

import static io.strimzi.systemtest.TestTags.BRIDGE;
import static io.strimzi.systemtest.TestTags.CONNECT;
import static io.strimzi.systemtest.TestTags.CRUISE_CONTROL;
import static io.strimzi.systemtest.TestTags.MIRROR_MAKER2;
import static io.strimzi.systemtest.TestTags.OLM;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag(OLM)
public class OlmSingleNamespaceST extends OlmAbstractST {

    public static final String NAMESPACE = "olm-namespace";

    @Test
    @Order(1)
    void testDeployExampleKafka() {
        doTestDeployExampleKafka();
    }

    @Test
    @Order(2)
    void testDeployExampleKafkaUser() {
        doTestDeployExampleKafkaUser();
    }

    @Test
    @Order(3)
    void testDeployExampleKafkaTopic() {
        doTestDeployExampleKafkaTopic();
    }

    @Test
    @Order(4)
    @Tag(CONNECT)
    void testDeployExampleKafkaConnect() {
        doTestDeployExampleKafkaConnect();
    }

    @Test
    @Order(5)
    @Tag(BRIDGE)
    void testDeployExampleKafkaBridge() {
        doTestDeployExampleKafkaBridge();
    }

    @Test
    @Order(7)
    @Tag(MIRROR_MAKER2)
    void testDeployExampleKafkaMirrorMaker2() {
        doTestDeployExampleKafkaMirrorMaker2();
    }

    @Test
    @Order(8)
    @Tag(CRUISE_CONTROL)
    void testDeployExampleKafkaRebalance() {
        doTestDeployExampleKafkaRebalance();
    }

    @BeforeAll
    void setup() {
        clusterOperator = clusterOperator.defaultInstallation()
            .withNamespace(NAMESPACE)
            .withWatchingNamespaces(NAMESPACE)
            .withBindingsNamespaces(Collections.singletonList(NAMESPACE))
            .createInstallation()
            // run always OLM installation
            .runOlmInstallation();

        cluster.setNamespace(NAMESPACE);
    }
}
