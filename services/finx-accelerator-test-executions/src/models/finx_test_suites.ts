import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn, ManyToOne, JoinColumn } from 'typeorm';
import { Finx_test_plane } from './finx_test_plane';

@Entity('finx_test_suites')
export class Finx_test_suites {
  @PrimaryGeneratedColumn('increment')
  id!: number;

  @Column({ unique: true })
  test_suite_name!: string

  @Column()
  test_plane_id!: number

  @ManyToOne(() => Finx_test_plane, { onDelete: 'CASCADE' })
  @JoinColumn({ name: 'test_plane_id' })
  test_plane!: Finx_test_plane;

  @Column({ default: 'active' })
  status!: string;

  @Column({ default: false })
  has_test_data!: boolean;

  @Column({ default: false })
  has_test_data_config!: boolean;

  @CreateDateColumn()
  createdAt!: Date;

  @UpdateDateColumn()
  updatedAt!: Date;
}