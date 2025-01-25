import { Component, Input } from '@angular/core';
import { Squadron } from '../../models/squadron/squadron';
import { MatCardModule } from '@angular/material/card'
import { NgIf } from '@angular/common';
import { SquadronService } from '../../services/squadron.service';
import { FormsModule } from '@angular/forms';
import { SquadronDTO } from '../../models/squadron/squadronDTO';

@Component({
  selector: 'app-squadron',
  imports: [MatCardModule, NgIf, FormsModule],
  templateUrl: './squadron.component.html',
  styleUrl: './squadron.component.css',
  standalone: true
})
export class SquadronComponent {
  constructor(private squadronService: SquadronService) {}

  @Input() squadron: Squadron| null = null;
  bufferSquadron: Squadron| null = null;
  editMode: boolean = false;

  toggleEditMode(): void {
    this.bufferSquadron = {...this.squadron!};
    this.editMode = !this.editMode;
    
    console.log(`${this.bufferSquadron?.maxCapacity}`)
    console.log(`${this.squadron?.maxCapacity}`)
  }

  submitChanges(): void {
    if (this.squadron) {
      const squadDTO = new SquadronDTO(this.squadron.squadronName, this.squadron.maxCapacity)
      this.squadronService.update(this.squadron.squadronId, squadDTO).subscribe(
        response => {
          this.editMode = false;
        },
        error => {
          alert('Error updating squadron: max capacity too low or 0');
          this.squadron = this.bufferSquadron;
        }
      );
    }
  }



}
