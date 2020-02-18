/* tslint:disable */
/* eslint-disable */
/**
 * 
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime';

export interface GetJWTRequest {
    username: string;
    password: string;
}

export interface VerifyJWTRequest {
    token: string;
}

/**
 * no description
 */
export class AuthApi extends runtime.BaseAPI {

    /**
     * Sign a new JWT token with username and password information
     */
    async getJWTRaw(requestParameters: GetJWTRequest): Promise<runtime.ApiResponse<string>> {
        if (requestParameters.username === null || requestParameters.username === undefined) {
            throw new runtime.RequiredError('username','Required parameter requestParameters.username was null or undefined when calling getJWT.');
        }

        if (requestParameters.password === null || requestParameters.password === undefined) {
            throw new runtime.RequiredError('password','Required parameter requestParameters.password was null or undefined when calling getJWT.');
        }

        const queryParameters: runtime.HTTPQuery = {};

        if (requestParameters.username !== undefined) {
            queryParameters['username'] = requestParameters.username;
        }

        if (requestParameters.password !== undefined) {
            queryParameters['password'] = requestParameters.password;
        }

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/auth/sign`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        });

        return new runtime.TextApiResponse(response) as any;
    }

    /**
     * Sign a new JWT token with username and password information
     */
    async getJWT(requestParameters: GetJWTRequest): Promise<string> {
        const response = await this.getJWTRaw(requestParameters);
        return await response.value();
    }

    /**
     * Verify a JWT token
     */
    async verifyJWTRaw(requestParameters: VerifyJWTRequest): Promise<runtime.ApiResponse<boolean>> {
        if (requestParameters.token === null || requestParameters.token === undefined) {
            throw new runtime.RequiredError('token','Required parameter requestParameters.token was null or undefined when calling verifyJWT.');
        }

        const queryParameters: runtime.HTTPQuery = {};

        if (requestParameters.token !== undefined) {
            queryParameters['token'] = requestParameters.token;
        }

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/auth/verify`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        });

        return new runtime.TextApiResponse(response) as any;
    }

    /**
     * Verify a JWT token
     */
    async verifyJWT(requestParameters: VerifyJWTRequest): Promise<boolean> {
        const response = await this.verifyJWTRaw(requestParameters);
        return await response.value();
    }

}
