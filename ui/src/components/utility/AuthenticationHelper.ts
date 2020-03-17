import Cookies from "js-cookie";
import {AuthApi} from "../../api/apis";

const isUserAuthenticated = async (token: string | undefined, authApi: AuthApi): Promise<boolean> => {
    if (token) {
        const requestParams = { token: token }
        console.log("Found JWT token, veryfing...")
        return authApi.verifyJWT(requestParams).then(verified => {
            console.log("JWT token status is " + verified)
            // @ts-ignore
            return verified == "true";
        })
    } else {
        console.log("No JWT token available")
        return false;
    }
}

export default isUserAuthenticated;