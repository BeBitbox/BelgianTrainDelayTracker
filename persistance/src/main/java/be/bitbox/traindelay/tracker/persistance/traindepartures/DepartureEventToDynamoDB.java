/*
 * Copyright 2019 Bitbox : TrainDelayTracker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.bitbox.traindelay.tracker.persistance.traindepartures;

import be.bitbox.traindelay.tracker.core.traindeparture.TrainDepartureEvent;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class DepartureEventToDynamoDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartureEventToDynamoDB.class);
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    DepartureEventToDynamoDB(AmazonDynamoDB client, EventBus eventBus) {
        dynamoDBMapper = new DynamoDBMapper(client);
        eventBus.register(this);
    }

    @Subscribe
    void subscribeDepartureEvent(TrainDepartureEvent trainDepartureEvent) {
        try {
            DynamoDepartureEvent dynamoItem = new DynamoDepartureEvent(trainDepartureEvent);
            LOGGER.debug("Saving improved dynamoItem {}", dynamoItem);
            dynamoDBMapper.save(dynamoItem);
        }
        catch (Exception e) {
            LOGGER.error("Failed to save improved event " + trainDepartureEvent, e);
        }
    }
}