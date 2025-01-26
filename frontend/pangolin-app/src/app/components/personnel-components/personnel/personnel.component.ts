import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Personnel } from '../../../models/personnel/personnel';
import { SquadronService } from '../../../services/squadron.service';
import { FormsModule } from '@angular/forms';
import { PersonnelService } from '../../../services/personnel.service';
import { PersonnelDTO } from '../../../models/personnel/personnelDTO';

@Component({
  selector: 'app-personnel',
  imports: [FormsModule, NgIf],
  templateUrl: './personnel.component.html',
  styleUrl: './personnel.component.css',
  standalone: true
})
export class PersonnelComponent {
  constructor(private squadronService: SquadronService, private personnelService: PersonnelService) {}
  @Input() person: Personnel | null = null;
  @Output() personDeleted = new EventEmitter<number>();
  @Output() personUpdated = new EventEmitter<Personnel>();
  squadName: string = "";
  bufferPerson: Personnel | null = null;
  editMode: boolean = false;

  ngOnInit(): void {
    if(this.person){
      this.squadronService.findOne(this.person.squadronId).subscribe((data) => {
        this.squadName = data.squadronName;
      })
    }
  }

  toggleEditMode(): void {
    this.bufferPerson = {...this.person!};
    this.editMode = !this.editMode;
  }

  submitChanges(): void {
    if (this.person) {
      const personnelDTO = new PersonnelDTO(
        this.person.personnelName,
        this.person.commander,
        this.person.squadronId
      );
  
      this.personnelService.update(this.person.personnelId, personnelDTO).subscribe(
        (response) => {
          this.editMode = false;
          // Create a new instance to avoid mutation issues
          const updatedPerson = new Personnel(
            this.person!.personnelId,
            this.person!.personnelName,
            this.person!.commander,
            this.person!.squadronId
          );
          this.personUpdated.emit(updatedPerson); // Emit updated personnel
        },
        (error) => {
          alert('Error updating personnel');
          this.person = this.bufferPerson; // Revert changes on error
        }
      );
    }
  }
  

  deletePersonnel(): void {
    if (this.person) {
      this.personnelService.delete(this.person.personnelId).subscribe(
        (response) => {
          alert('Personnel deleted');
          this.personDeleted.emit(this.person!.personnelId);
        },
        (error) => {
          alert('Error deleting personnel');
        }
      );
    }
  }
}
