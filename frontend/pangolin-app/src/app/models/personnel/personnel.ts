import { deserialize } from '../utils/deserialize';

export interface ApiPersonnelModel {
  personnelId: number;
  personnelName: string;
  commander: boolean;
  squadronId: number;
}

export class Personnel {
  public personnelId: number;
  public personnelName: string;
  public commander: boolean;
  public squadronId: number;

  constructor(
    dataOrPersonnelId: ApiPersonnelModel | number,
    personnelName?: string,
    commander?: boolean,
    squadronId?: number
  ) {
    if (typeof dataOrPersonnelId === 'object') {
      this.personnelId = dataOrPersonnelId.personnelId;
      this.personnelName = dataOrPersonnelId.personnelName;
      this.commander = dataOrPersonnelId.commander;
      this.squadronId = dataOrPersonnelId.squadronId;
    } else {
      this.personnelId = dataOrPersonnelId;
      this.personnelName = personnelName!;
      this.commander = commander!;
      this.squadronId = squadronId!;
    }
  }
  // Used to deserialize API model of a personnel
  static fromApiData(data: ApiPersonnelModel): Personnel {
    return deserialize(Personnel, data);
  }
}
