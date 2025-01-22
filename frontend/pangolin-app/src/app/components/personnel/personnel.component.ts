import { NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Personnel } from '../../models/personnel/personnel';
import { SquadronService } from '../../services/squadron.service';

@Component({
  selector: 'app-personnel',
  imports: [MatCardModule, NgIf],
  templateUrl: './personnel.component.html',
  styleUrl: './personnel.component.css',
  standalone: true
})
export class PersonnelComponent {
  constructor(private squadronService: SquadronService) {}
  @Input() person: Personnel | null = null;
  squadName: string = "";

  ngOnInit(): void {
    if(this.person){
      this.squadronService.findOne(this.person.squadronId).subscribe((data) => {
        this.squadName = data.squadronName;
      })
    }
  }
}
