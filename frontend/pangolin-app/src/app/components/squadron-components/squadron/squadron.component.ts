import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Squadron } from '../../../models/squadron/squadron';
import { MatCardModule } from '@angular/material/card'
import { NgIf } from '@angular/common';
import { SquadronService } from '../../../services/squadron.service';
import { FormsModule } from '@angular/forms';
import { SquadronDTO } from '../../../models/squadron/squadronDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-squadron',
  imports: [MatCardModule, NgIf, FormsModule],
  templateUrl: './squadron.component.html',
  styleUrl: './squadron.component.css',
  standalone: true
})
export class SquadronComponent {
  constructor(private squadronService: SquadronService, private router: Router) {
    // Update the current route whenever it changes
    this.router.events.subscribe(() => {
      this.currentRoute = this.router.url; // Get the current URL
    });
  }
  currentRoute: string = '';
  @Input() squadron: Squadron| null = null;
  @Output() squadronDeleted = new EventEmitter<number>();
  bufferSquadron: Squadron| null = null;
  editMode: boolean = false;

  toggleEditMode(): void {
    this.bufferSquadron = {...this.squadron!};
    this.editMode = !this.editMode;
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

  deleteSquad(): void {
    if (this.squadron) {
      this.squadronService.delete(this.squadron.squadronId).subscribe(
        response => {
          // notify squadron-view component of deletion
          this.squadronDeleted.emit(this.squadron!.squadronId);
        },
        error => {
          alert('Error deleting squadron');
        }
      )
    }
  }

  // Navigate to the manage-squadron route
  goToManageSquadron(): void {
    if (this.squadron?.squadronId) {
      this.router.navigate(['/manage-squadron', this.squadron.squadronId]);
    }
  }
}
