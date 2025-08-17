// CRUD
import { Injectable } from '@nestjs/common';
import { finx_test_executionsRepository } from '../repositories/finx_test_executionsRepository';
import { Ifinx_test_executions } from '../models/finx_test_executions';

@Injectable()
class finx_test_executionsService {
    constructor(private readonly finx_test_executionsRepository: finx_test_executionsRepository) {}
    async createFinx_test_executions(data: any) {
        return this.finx_test_executionsRepository.create(data);
    }

    async getFinx_test_executions(id?: string) {
        return await this.finx_test_executionsRepository.findAll(id);
    }

    async getFinx_test_executionsById(id: string) {
        return await this.finx_test_executionsRepository.findById(id);
    }

    async updateFinx_test_executions(id: string, updatedData: Partial<Ifinx_test_executions>) {
        return await this.finx_test_executionsRepository.updateById(id, updatedData);
    }

    async deleteFinx_test_executions(id: string) {
        return await this.finx_test_executionsRepository.deleteById(id);
    }
}

export { finx_test_executionsService };
