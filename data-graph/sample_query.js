const https = require('https');

const BEARER_TOKEN = 'YOUR_TOKEN_HERE';

const query = `
query {
  getByAddress(address: "4700 CALLE BOLERO, CAMARILLO, CA 93012") {
    addresses {
      data {
        crimeIndex {
          data {
            violentCrimeIndexNational
            propertyCrimeIndexNational
            compositeCrimeCategory { value description }
          }
        }
        earthRisk {
          data { nameOfNearestFault distanceToNearestFaultMiles faultType }
        }
      }
    }
  }
}`;

var options = {
    hostname: 'data-graph-dev.libigdata.cloud.precisely.services',
    path: '/data-graph/graphql',
    method: 'POST',
    headers: {
        'Authorization': `Bearer ${BEARER_TOKEN}`,
        'Content-Type': 'application/json',
    }
}

const req = https.request(options, res => {
  let body = '';
  res.on('data', chunk => body += chunk);
  res.on('end', () => console.log(body));
});

req.on('error', console.error);
req.write(JSON.stringify({ query }));
req.end();
