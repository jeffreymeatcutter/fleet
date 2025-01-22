import { deserialize } from "../utils/deserialize";

export class Squadron {
    public squadronId: number;
    public squadronName: string;
    public maxCapacity: number;

    constructor(
        dataOrSquadronId: ApiSquadronModel | number,
        squadronName?: string,
        maxCapacity?: number
    ) {
        if (typeof dataOrSquadronId === 'object'){
            this.squadronId = dataOrSquadronId.squadronId;
            this.squadronName = dataOrSquadronId.squadronName;
            this.maxCapacity = dataOrSquadronId.maxCapacity;
        }else{
            this.squadronId = dataOrSquadronId;
            this.squadronName = squadronName!;
            this.maxCapacity = maxCapacity!;
        }
    }
    // Used to deserialize API model of a squadron
      static fromApiData(data: ApiSquadronModel): Squadron {
        return deserialize(Squadron, data);
      }
}

export interface ApiSquadronModel {
    squadronId: number;
    squadronName: string;
    maxCapacity: number;
}