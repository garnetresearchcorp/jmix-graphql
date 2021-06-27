/*
 * Copyright 2021 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.graphql.custom

import io.jmix.graphql.AbstractGraphQLTest
import org.springframework.context.annotation.ComponentScan

@ComponentScan("io.jmix.graphql.custom.service")
class CustomMutationTest extends AbstractGraphQLTest{

    def id1 = "4c34985b-67be-4788-891a-839d479bf9e6"

    def "create new Car instance, modified this and return additional fetched attributes and _instanceName"() {
        when:
        def response = query(
                "datafetcher/upsert-car-return-instance-name.gql", asObjectNode("{\"id\":\"$id1\"}"))
        then:
        response.get('$.data.upsert_scr_Car._instanceName') == "TESLA - Z"
        response.get('$.data.upsert_scr_Car.price') == "10"
        response.get('$.data.upsert_scr_Car.maxPassengers') == "5"
    }
}
