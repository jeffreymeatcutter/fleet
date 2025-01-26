import { Component } from '@angular/core';
import { ShipsService } from '../../../services/ships.service';
import { ShipsDTO } from '../../../models/ships/shipsDTO';
import { ShipType } from '../../../models/ships/ships';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ships-add',
  imports: [FormsModule, CommonModule],
  templateUrl: './ships-add.component.html',
  styleUrls: ['./ships-add.component.css'],
})
export class ShipsAddComponent {
  errorMessage: string = '';
  shipToCreate: ShipsDTO = new ShipsDTO('', ShipType.Destroyer, 1); // Default squadronId = 1
  shipTypes = Object.values(ShipType); // Enum values for Ship Types

  constructor(private shipsService: ShipsService) {}

  createShip(): void {
    if (!this.shipToCreate.shipName.trim()) {
      this.errorMessage = 'Ship name is required.';
      return;
    }

    // Call the ShipsService to save the ship
    this.shipsService.create(this.shipToCreate).subscribe(
      (response) => {
        alert('Ship created successfully!');
        this.resetForm();
      },
      (error) => {
        this.errorMessage = 'Error creating ship. Please try again.';
      }
    );
  }

  resetForm(): void {
    this.shipToCreate = new ShipsDTO('', ShipType.Destroyer, 1); // Reset with default values
    this.errorMessage = '';
  }
}
