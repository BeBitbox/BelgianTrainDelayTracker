/*
 * Copyright 2018 Bitbox : TrainDelayTracker
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
package be.bitbox.traindelay.tracker.nmbs.request;

import java.time.LocalDate;

public class RequestFactory {

    private String stationName;

    private String stationId;

    private LocalDate date;

    private RequestFactory() {
    }

    public static RequestFactory aRequest(){
        return new RequestFactory();
    }

    public RequestFactory withStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public RequestFactory withStationId(String stationId) {
        this.stationId = stationId;
        return this;
    }

    public RequestFactory withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Request build() {
        return new Request(stationName, stationId, date);
    }

}