import { Component } from '@angular/core';
import { Personnel } from '../../../models/personnel/personnel';
import { PersonnelService } from '../../../services/personnel.service';
import { NgForOf, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { PersonnelComponent } from '../personnel/personnel.component';

@Component({
  selector: 'app-personnel-view',
  imports: [NgForOf, NgIf, PersonnelComponent],
  templateUrl: './personnel-view.component.html',
  styleUrl: './personnel-view.component.css',
  standalone: true
})
export class PersonnelViewComponent {
  personnel: Personnel[] = [];

  constructor(private personnelService: PersonnelService, private router: Router) {}

  ngOnInit() {
      //Populate personnel on init
      this.loadPersonnel();
    }

    loadPersonnel(): void {
      this.personnelService.findAll().subscribe((data) => {
        this.personnel = data;
      });
    }

    // Function to track items by their unique ID
    trackByPersonId(index: number, person: Personnel): number | undefined {
      return person.personnelId;
    }
    // Handle person deletion
  handlePersonDeletion(personnelId: number): void {
    this.personnel = this.personnel.filter(personnel => personnel.personnelId !== personnelId);
  }
   // Navigate to the add-squadron route
   goToAddPersonnel(): void {
    this.router.navigate(['/add-personnel']);
  }


  handlePersonnelUpdate(updatedPersonnel: Personnel): void {
    this.loadPersonnel();
  }
}
