interface Configuration {
    apiHost: string
}

const config: { [environment: string]: Configuration } = {
    "development": {
        apiHost: "http://localhost:8080/api"
    },
    "testing": {
        apiHost: "http://localhost:8080/api"
    },
    "production": {
        apiHost: "http://simplelinuxvm-bawdzgnfesgv2.centralus.cloudapp.azure.com:8080/api"
    }
}



export default config[process.env.NODE_ENV as string];
