package com.epam.entity;

import com.epam.entity.type.*;

import java.util.Objects;

public class Table extends BaseEntity<Table> {
    private int seatsNumber;
    private TableType value;
    private TableStatus status;
    private int reservationTime;

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public TableType getValue() {
        return value;
    }

    public void setValue(TableType value) {
        this.value = value;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public int getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(int reservationTime) {
        this.reservationTime = reservationTime;
    }

    @Override
    public String toString() {
        return super.toString() + " " + seatsNumber + " " + value + " " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return seatsNumber == table.seatsNumber && value == table.value && status == table.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatsNumber, value, status, reservationTime);
    }
}
