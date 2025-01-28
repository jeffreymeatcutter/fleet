import { Component } from '@angular/core';
import { SquadronService } from '../../../services/squadron.service';
import { PersonnelService } from '../../../services/personnel.service';
import { ActivatedRoute } from '@angular/router';
import { Squadron } from '../../../models/squadron/squadron';
import { Ships } from '../../../models/ships/ships';
import { Personnel } from '../../../models/personnel/personnel';
import { ShipsService } from '../../../services/ships.service';
import { ShipsComponent } from '../../ships-components/ships/ships.component';
import { SquadronComponent } from '../squadron/squadron.component';
import { PersonnelComponent } from '../../personnel-components/personnel/personnel.component';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-squadron-manage',
  imports: [ShipsComponent, SquadronComponent, PersonnelComponent, NgForOf, NgIf],
  templateUrl: './squadron-manage.component.html',
  styleUrl: './squadron-manage.component.css',
  standalone: true,
})
export class SquadronManageComponent {
  constructor(
    private squadronService: SquadronService,
    private personnelService: PersonnelService,
    private shipsService: ShipsService,
    private route: ActivatedRoute
  ) {}

  squadronId: number | null = null;
  squadron: Squadron | null = null;
  assignedShips: Ships[] = [];
  unassignedShips: Ships[] = [];
  assignedPersonnel: Personnel[] = [];
  unassignedPersonnel: Personnel[] = [];
  errorMessage: string = '';

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.squadronId = +params['squadronId']; // Convert string to number
      if (this.squadronId) {
        this.fetchData();
      }
    });
  }

  fetchData(): void {
    if (this.squadronId) {
      // Fetch squadron details
      this.squadronService.findOne(this.squadronId).subscribe(
        (data) => {
          this.squadron = data;
        },
        (error) => {
          this.errorMessage = 'Error fetching squadron data.';
        }
      );

      // Fetch personnel associated with the squadron
      this.personnelService.findPersonnelBySquadronId(this.squadronId).subscribe(
        (data) => {
          this.assignedPersonnel = data;
        },
        (error) => {
          this.errorMessage = 'Error fetching personnel data.';
        }
      );

      // Fetch ships associated with the squadron
      this.squadronService.findShipsBySquadronId(this.squadronId).subscribe(
        (data) => {
          this.assignedShips = data;
        },
        (error) => {
          this.errorMessage = 'Error fetching ships data.';
        }
      );
      // Fetch personnel associated with the unassigned squadron
      this.personnelService.findPersonnelBySquadronId(1).subscribe(
        (data) => {
          this.unassignedPersonnel = data;
        },
        (error) => {
          this.errorMessage = 'Error fetching unassigned personnel data.';
        }
      );
      // Fetch ships associated with the unassigned squadron
      this.squadronService.findShipsBySquadronId(1).subscribe(
        (data) => {
          this.unassignedShips = data;
        },
        (error) => {
          this.errorMessage = 'Error fetching unassigned ships data.';
        }
      );
    }
  }

  deassignPersonnel(personnel: Personnel): void {
    if (personnel) {
      const updatedPersonnel = { ...personnel, squadronId: 1 }; // Assign to unassigned squadron
      this.personnelService.update(personnel.personnelId, updatedPersonnel).subscribe(
        () => {
          this.fetchData(); // Refresh data
        },
        (error) => {
          console.error('Error deassigning personnel:', error);
        }
      );
    }
  }
  
  deassignShip(ship: Ships): void {
    if (ship) {
      const updatedShip = { ...ship, squadronId: 1 }; // Assign to unassigned squadron
      this.shipsService.update(ship.shipId, updatedShip).subscribe(
        () => {
          this.fetchData(); // Refresh data
        },
        (error) => {
          console.error('Error deassigning ship:', error);
        }
      );
    }
  }
  

  assignPersonnel(personId: number): void {
    const personnelToUpdate = this.unassignedPersonnel.find((p) => p.personnelId === personId);
    if (personnelToUpdate && this.squadronId) {
      const personnelDTO = { ...personnelToUpdate, squadronId: this.squadronId }; // Assign to current squadron
      this.personnelService.update(personId, personnelDTO).subscribe(
        () => {
          this.fetchData(); // Refresh the data after assignment
        },
        (error) => {
          alert('Error assigning personnel');
        }
      );
    }
  }
  
  assignShip(shipId: number): void {
    const shipToUpdate = this.unassignedShips.find((s) => s.shipId === shipId);
    if (shipToUpdate && this.squadronId) {
      const shipDTO = { ...shipToUpdate, squadronId: this.squadronId }; // Assign to current squadron
      this.shipsService.update(shipId, shipDTO).subscribe(
        () => {
          this.fetchData(); // Refresh the data after assignment
        },
        (error) => {
          alert('Error assigning ship');
        }
      );
    }
  }
  
}
