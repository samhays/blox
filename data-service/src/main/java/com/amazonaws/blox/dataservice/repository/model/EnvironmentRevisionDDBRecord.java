/*
 * Copyright 2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may
 * not use this file except in compliance with the License. A copy of the
 * License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.blox.dataservice.repository.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "EnvironmentRevisions")
@Data
@Builder
//Required for builder because an empty constructor exists
@AllArgsConstructor
//Required for dynamodbmapper
@NoArgsConstructor
public class EnvironmentRevisionDDBRecord {

  public static final String ENVIRONMENT_ID_HASH_KEY = "environmentId";
  public static final String ENVIRONMENT_REVISION_ID_RANGE_KEY = "environmentRevisionId";

  public static EnvironmentRevisionDDBRecord withKeys(
      final String environmentId, final String environmentRevisionId) {
    return EnvironmentRevisionDDBRecord.builder()
        .environmentId(environmentId)
        .environmentRevisionId(environmentRevisionId)
        .build();
  }

  @DynamoDBHashKey(attributeName = ENVIRONMENT_ID_HASH_KEY)
  private String environmentId;

  @DynamoDBRangeKey(attributeName = ENVIRONMENT_REVISION_ID_RANGE_KEY)
  private String environmentRevisionId;

  @DynamoDBVersionAttribute private Long recordVersion;

  //encoded name:value
  private Set<String> attributes;
  private String clusterName;
  private String taskDefinition;
}
