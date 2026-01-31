export type Installment = {
    id: number;
    description: string;
    totalAmount: number;
    totalParcels: number;
    startDate: string;
    status: 'ACTIVE' | 'FINISHED'; 
    category: string | null;
};