import { deserialize } from '../utils/deserialize';

export class Ships {
  public shipId: number;
  public shipName: string;
  public shipType: ShipType;
  public squadronId: number;

  constructor(
    dataOrShipId: ApiShipsModel | number,
    shipName?: string,
    shipType?: ShipType,
    squadronId?: number
  ) {
    if (typeof dataOrShipId === 'object') {
      // Case when an object of type ApiShipsModel is passed
      this.shipId = dataOrShipId.shipId;
      this.shipName = dataOrShipId.shipName;
      this.shipType = dataOrShipId.shipType;
      this.squadronId = dataOrShipId.squadronId;
    } else {
      // Case when individual parameters are passed
      this.shipId = dataOrShipId;
      this.shipName = shipName!;
      this.shipType = shipType!;
      this.squadronId = squadronId!;
    }
  }
  // Used to deserialize API model of a ship
  static fromApiData(data: ApiShipsModel): Ships {
    return deserialize(Ships, data);
  }
}
export interface ApiShipsModel {
  shipId: number;
  shipName: string;
  shipType: ShipType;
  squadronId: number;
}
export enum ShipType {
  Destroyer = 'destroyer',
  Carrier = 'carrier',
  Battleship = 'battleship',
  Cruiser = 'cruiser',
  Frigate = 'frigate',
}
