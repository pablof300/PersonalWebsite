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
        apiHost: "https://pabloestrada.me:8443/api"
    }
}



export default config[process.env.NODE_ENV as string];
