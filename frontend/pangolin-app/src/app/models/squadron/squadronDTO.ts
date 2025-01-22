export class SquadronDTO {
    public squadronName: string;
    public maxCapacity: number;

    constructor(
        squadronName: string,
        maxCapacity: number
    ){
        this.squadronName = squadronName;
        this.maxCapacity = maxCapacity;
    }
}