export class PersonnelDTO {
  public personnelName: string;
  public isCommander: boolean;
  public squadronId: number;

  constructor(personnelName: string, isCommander: boolean, squadronId: number) {
    this.personnelName = personnelName;
    this.isCommander = isCommander;
    this.squadronId = squadronId;
  }
}
