import { Component } from '@angular/core';
import { Personnel } from '../../models/personnel/personnel';
import { PersonnelService } from '../../services/personnel.service';
import { CommonModule } from '@angular/common';
import { PersonnelComponent } from '../personnel/personnel.component';

@Component({
  selector: 'app-personnel-view',
  imports: [CommonModule, PersonnelComponent],
  templateUrl: './personnel-view.component.html',
  styleUrl: './personnel-view.component.css',
  standalone: true
})
export class PersonnelViewComponent {
  personnel: Personnel[] = [];

  constructor(private personnelService: PersonnelService) {}

  ngOnInit() {
      //Populate personnel on init
      this.personnelService.findAll().subscribe((data) => {
        this.personnel = data;
      });
    }
    // Function to track items by their unique ID
    trackByPersonId(index: number, person: Personnel): number | undefined {
      return person.personnelId;
    }
}
