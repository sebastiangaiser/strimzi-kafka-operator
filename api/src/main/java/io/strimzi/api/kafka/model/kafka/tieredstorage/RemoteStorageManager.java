/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */

package io.strimzi.api.kafka.model.kafka.tieredstorage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.strimzi.api.kafka.model.common.Constants;
import io.strimzi.api.kafka.model.common.UnknownPropertyPreserving;
import io.strimzi.crdgenerator.annotations.Description;
import io.sundr.builder.annotations.Buildable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Configures a RemoteStorageManager in the tiered storage setup.
 */
@Buildable(
    editableEnabled = false,
    builderPackage = Constants.FABRIC8_KUBERNETES_API
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"className", "classPath", "config"})
@EqualsAndHashCode
@ToString
public class RemoteStorageManager implements UnknownPropertyPreserving {
    private String className;
    private String classPath;
    private Map<String, String> config = new HashMap<>(0);
    protected Map<String, Object> additionalProperties;

    @Description("The class name for the `RemoteStorageManager` implementation.")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Description("The class path for the `RemoteStorageManager` implementation.")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    @Description("The additional configuration map for the `RemoteStorageManager` implementation. " +
        "Keys will be automatically prefixed with `rsm.config.`, and added to Kafka broker configuration.")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    @Override
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties != null ? this.additionalProperties : Map.of();
    }

    @Override
    public void setAdditionalProperty(String name, Object value) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap<>(2);
        }
        this.additionalProperties.put(name, value);
    }
}
