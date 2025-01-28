import { Component } from '@angular/core';
import { Ships } from '../../../models/ships/ships';
import { ShipsService } from '../../../services/ships.service';
import { NgForOf, NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { ShipsComponent } from '../ships/ships.component';

@Component({
  selector: 'app-ships-view',
  imports: [NgForOf, NgIf, ShipsComponent],
  templateUrl: './ships-view.component.html',
  styleUrl: './ships-view.component.css',
  standalone: true
})
export class ShipsViewComponent {
  ships: Ships[] = [];

  constructor(private shipsService: ShipsService, private router: Router) {}

  ngOnInit() {
    // Populate ships on init
    this.loadShips();
  }

  loadShips(): void {
    this.shipsService.findAll().subscribe((data) => {
      this.ships = data;
    });
  }

  // Function to track items by their unique ID
  trackByShipId(index: number, ship: Ships): number | undefined {
    return ship.shipId;
  }

  // Handle ship deletion
  handleShipDeletion(shipId: number): void {
    this.ships = this.ships.filter(ship => ship.shipId !== shipId);
  }

  goToAddShip(): void {
    this.router.navigate(['/add-ships']);
  }

  // Handle ship update
  handleShipUpdate(updatedShip: Ships): void {
    this.loadShips();
  }
}

