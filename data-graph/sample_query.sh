curl -X POST https://data-graph-dev.libigdata.cloud.precisely.services/data-graph/graphql \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
    "query": "query GetByAddress { getByAddress(address: \"4700 CALLE BOLERO, CAMARILLO, CA 93012\") { addresses { data { crimeIndex { data { violentCrimeIndexNational propertyCrimeIndexNational compositeCrimeCategory { value description } } } earthRisk { data { nameOfNearestFault distanceToNearestFaultMiles faultType } } } } } }"
  }'