# pip3 install requests

import requests
import json

url = "https://data-graph-dev.libigdata.cloud.precisely.services/data-graph/graphql"

QUERY = """
query GetByAddress {
  getByAddress(address: "4700 CALLE BOLERO, CAMARILLO, CA 93012") {
    addresses {
      data {
        crimeIndex {
          data { violentCrimeIndexNational propertyCrimeIndexNational compositeCrimeCategory { value description }
          }
        }
        earthRisk {
          data { nameOfNearestFault distanceToNearestFaultMiles faultType }
        }
      }
    }
  }
}
"""

payload = {
    "query": QUERY
}

TOKEN = "YOUR_TOKEN_HERE"

headers = {
    "Authorization": f"Bearer {TOKEN}",
    "Content-Type": "application/json"
}

response = requests.post(url, headers=headers, json=payload)

print(json.dumps(response.json(), indent=2))
