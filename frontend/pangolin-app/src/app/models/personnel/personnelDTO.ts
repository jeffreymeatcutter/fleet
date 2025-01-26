export class PersonnelDTO {
  public personnelName: string;
  public commander: boolean;
  public squadronId: number;

  constructor(personnelName: string, commander: boolean, squadronId: number) {
    this.personnelName = personnelName;
    this.commander = commander;
    this.squadronId = squadronId;
  }
}
