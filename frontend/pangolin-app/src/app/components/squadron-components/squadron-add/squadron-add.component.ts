import { Component } from '@angular/core';
import { SquadronService } from '../../../services/squadron.service';
import { SquadronDTO } from '../../../models/squadron/squadronDTO';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-squadron-add',
  imports: [FormsModule, NgIf],
  templateUrl: './squadron-add.component.html',
  styleUrl: './squadron-add.component.css',
  standalone: true
})
export class SquadronAddComponent {
  errorMessage: string = "";
  squadToCreate: SquadronDTO = new SquadronDTO("", 20);
  constructor(private squadronService: SquadronService) {}

  createSquadron(): void {
    if (this.squadToCreate) {
      // Validate maxCapacity
      if (this.squadToCreate.maxCapacity <= 0) {
        this.errorMessage = 'Max capacity must be a positive number greater than 0.';
        return;
      }
  
      // Reset error message if validation passes
      this.errorMessage = '';
  
      // Call the API to create the squadron
      this.squadronService.create(this.squadToCreate).subscribe(
        response => {
          alert('Squadron created successfully!');
          this.squadToCreate = new SquadronDTO("", 20);
        },
        error => {
          alert('Error creating squadron. Please try again.');
        }
      );
    }
  }
  
}
