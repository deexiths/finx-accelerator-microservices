import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn } from 'typeorm';

@Entity('finx_test_plane') // Specify the table name if needed
export class Finx_test_plane {
  @PrimaryGeneratedColumn('increment') // Use UUID or other generation strategy
    id!: number;

      @Column ({unique: true})
      test_plane_name!:string

      @Column ({unique: true})
      customer_name!:string

      @Column ({unique: true})
      project_name!:string

  @CreateDateColumn()
    createdAt!: Date;

  @UpdateDateColumn()
    updatedAt!: Date;
}