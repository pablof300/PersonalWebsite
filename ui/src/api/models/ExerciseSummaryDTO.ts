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

import { exists, mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface ExerciseSummaryDTO
 */
export interface ExerciseSummaryDTO {
    /**
     * 
     * @type {number}
     * @memberof ExerciseSummaryDTO
     */
    lengthOfStreakInDays?: number;
    /**
     * 
     * @type {number}
     * @memberof ExerciseSummaryDTO
     */
    milesRanToday?: number;
    /**
     * 
     * @type {number}
     * @memberof ExerciseSummaryDTO
     */
    numberOfMinutesInGym?: number;
}

export function ExerciseSummaryDTOFromJSON(json: any): ExerciseSummaryDTO {
    return ExerciseSummaryDTOFromJSONTyped(json, false);
}

export function ExerciseSummaryDTOFromJSONTyped(json: any, ignoreDiscriminator: boolean): ExerciseSummaryDTO {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'lengthOfStreakInDays': !exists(json, 'lengthOfStreakInDays') ? undefined : json['lengthOfStreakInDays'],
        'milesRanToday': !exists(json, 'milesRanToday') ? undefined : json['milesRanToday'],
        'numberOfMinutesInGym': !exists(json, 'numberOfMinutesInGym') ? undefined : json['numberOfMinutesInGym'],
    };
}

export function ExerciseSummaryDTOToJSON(value?: ExerciseSummaryDTO | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'lengthOfStreakInDays': value.lengthOfStreakInDays,
        'milesRanToday': value.milesRanToday,
        'numberOfMinutesInGym': value.numberOfMinutesInGym,
    };
}


