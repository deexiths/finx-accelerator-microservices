// CRUD
import { Injectable,HttpException,HttpStatus } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Finx_test_executions } from '../models/finx_test_executions';
import { Ifinx_test_executions } from '../models/finx_test_executions';

@Injectable()
export class finx_test_executionsRepository {
  constructor(
    @InjectRepository(Finx_test_executions)
    private repo: Repository<Finx_test_executions>
  ) {}

  async create(data: Partial<Ifinx_test_executions>) {
    try {
    const newSuite = this.repo.create(data);
    const response = await this.repo.save(newSuite);
    return response;
    } catch (error:any) {
      throw new HttpException(
        {
          success: false,
          message: error.message,
        },
        HttpStatus.CONFLICT
      );
    }
  }

  findAll(id?: string) {
    const queryOptions: any = {
      relations: ['test_suite', 'test_suite.test_plane'], // include nested relation
      order: {
      updatedAt: 'DESC', 
      }
    };
 
    if (id) {
      queryOptions.where = { test_suite_id: Number(id) };
    }
  
    return this.repo.find(queryOptions).then((executions:any) =>
      executions.map((ex:any) => ({
        id: ex.id,
        test_report_name: ex.test_report_name,
        status: ex.status,
        test_suite_name: ex.test_suite?.test_suite_name,
        test_plane_name: ex.test_suite?.test_plane?.test_plane_name,
        createdAt: ex.createdAt,
        updatedAt: ex.updatedAt,
      }))
    );
  }

  findById(id: string) {
    return this.repo.findOne({ relations:['test_suite'], where: { id: Number(id) } });
  }

  updateById(id: string, data: Partial<Ifinx_test_executions>) {
    return this.repo.update(Number(id), data);
  }

  deleteById(id: string) {
    return this.repo.delete(Number(id));
  }
}
