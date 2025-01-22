import { ShipType } from '../ships/ships'

export class ShipsDTO {
    public shipName: string;
    public shipType: ShipType;
    public squadronId: number;

    constructor(
        shipId: number,
        shipName: string,
        shipType: ShipType,
        squadronId: number
    ){
        this.shipName = shipName;
        this.shipType = shipType;
        this.squadronId = squadronId;
    }
}