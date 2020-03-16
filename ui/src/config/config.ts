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
        apiHost: "http://persian_rest:8080/api"
    }
}



export default config[process.env.NODE_ENV as string];
