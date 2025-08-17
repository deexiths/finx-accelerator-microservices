// CRUD
import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn,JoinColumn,ManyToOne,UpdateDateColumn } from 'typeorm';
import { Finx_test_suites } from './finx_test_suites';

export interface Ifinx_test_executions {

  test_suite_id: number;

  test_report_name: string;

  status: string;
}

@Entity('finx_test_executions') // Specify the table name if needed
export class Finx_test_executions {
  @PrimaryGeneratedColumn('increment')
  id!: number;

  @Column()
  test_suite_id!: number; // Store just the foreign key value

  @ManyToOne(() => Finx_test_suites, { onDelete: 'CASCADE' })
    @JoinColumn({ name: 'test_suite_id' })
    test_suite!: Finx_test_suites;

  @CreateDateColumn()
  createdAt!: Date;

  @UpdateDateColumn()
  updatedAt!: Date;

  @Column({ unique: true })
  test_report_name!: string;

  @Column()
  status!: string;
}
