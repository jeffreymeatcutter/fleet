import { Component } from '@angular/core';
import { PersonnelDTO } from '../../../models/personnel/personnelDTO';
import { PersonnelService } from '../../../services/personnel.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-personnel-add',
  imports: [FormsModule, CommonModule],
  templateUrl: './personnel-add.component.html',
  styleUrl: './personnel-add.component.css'
})
export class PersonnelAddComponent {
   errorMessage: string = "";
    personToCreate: PersonnelDTO = new PersonnelDTO("", false, 1);
    constructor(private personnelService: PersonnelService) {}
  
    createPersonnel(): void {
      if (!this.personToCreate.personnelName.trim()) {
        this.errorMessage = 'Personnel name is required.';
        return;
      }
  
      // Call the PersonnelService to save the personnel
      this.personnelService.create(this.personToCreate).subscribe(
        (response) => {
          alert('Personnel created successfully!');
          this.resetForm();
        },
        (error) => {
          this.errorMessage = 'Error creating personnel. Please try again.';
        }
      );
    }
    resetForm(): void {
      this.personToCreate = new PersonnelDTO('', false, 1); // Reset with default values
      this.errorMessage = '';
    }
}
