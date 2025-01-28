import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ShipType, Ships } from '../../../models/ships/ships';
import { SquadronService } from '../../../services/squadron.service';
import { ShipsService } from '../../../services/ships.service';
import { ShipsDTO } from '../../../models/ships/shipsDTO';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ships',
  imports: [FormsModule, CommonModule],
  templateUrl: './ships.component.html',
  styleUrls: ['./ships.component.css'],
  standalone: true
})
export class ShipsComponent {
  @Input() ship: Ships | null = null;
  @Output() shipUpdated = new EventEmitter<Ships>();
  @Output() shipDeleted = new EventEmitter<number>();
  
  squadName: string = '';
  bufferShip: Ships | null = null;
  editMode: boolean = false;
  shipTypes = Object.values(ShipType); // Enum values for Ship Type

  constructor(private squadronService: SquadronService, private shipsService: ShipsService) {}

  ngOnInit(): void {
    if (this.ship) {
      this.squadronService.findOne(this.ship.squadronId).subscribe((data) => {
        this.squadName = data.squadronName;
      });
    }
  }

  toggleEditMode(): void {
    this.bufferShip = { ...this.ship! };
    this.editMode = !this.editMode;
  }

  submitChanges(): void {
    if (this.ship) {
      // Create a ShipDTO to send to the API (abstracting the shipId)
      const shipDTO = new ShipsDTO(
        this.ship.shipName,
        this.ship.shipType,
        this.ship.squadronId
      );

      this.shipsService.update(this.ship.shipId, shipDTO).subscribe(
        (response) => {
          this.editMode = false;
          // Update the ship with the new data (if needed)
          this.shipUpdated.emit(new Ships(
            this.ship!.shipId,
            this.ship!.shipName,
            this.ship!.shipType,
            this.ship!.squadronId
          )); // Emit the updated ship
        },
        (error) => {
          alert('Error updating ship');
          this.ship = this.bufferShip; // Revert changes on error
        }
      );
    }
  }

  deleteShip(): void {
    if (this.ship) {
      this.shipsService.delete(this.ship.shipId).subscribe(
        (response) => {
          alert('Ship deleted');
          this.shipDeleted.emit(this.ship!.shipId);
        },
        (error) => {
          alert('Error deleting ship');
        }
      );
    }
  }
}
