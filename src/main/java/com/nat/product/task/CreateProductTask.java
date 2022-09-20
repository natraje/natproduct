package com.nat.product.task;

import com.nat.product.dto.ProductVO;
import com.nat.product.model.Product;
import com.nat.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CreateProductTask extends RecursiveTask<List<ProductVO>> {
    private int start;
    private int end;
    List<ProductVO> vos;
    int threshold=4;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

//    public CreateProductTask(int start, int end, List<ProductVO> vos) {
//        this.start = start;
//        this.end = end;
//        this.vos = vos;
//    }

    public CreateProductTask(int start, int end, List<ProductVO> vos, ProductRepository productRepository, ModelMapper modelMapper) {
        this.start = start;
        this.end = end;
        this.vos = vos;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    protected List<ProductVO> compute() {
        int length = end - start;
        if (length <= threshold) {
            List<ProductVO> p1=new ArrayList<>();
            for(int i=start;i<end;i++) {
                Product productNew = modelMapper.map(vos.get(i), Product.class);
                productNew = productRepository.save(productNew);
                ProductVO vo = modelMapper.map(productNew, ProductVO.class);
                p1.add(vo);

            }
            return p1;
        } else{
            CreateProductTask firstTask = new CreateProductTask(start, start + length / 2,vos,productRepository,modelMapper);
            firstTask.fork(); //start asynchronously

            CreateProductTask secondTask = new CreateProductTask(start + length / 2, end,vos,productRepository,modelMapper);

            List<ProductVO> p1 = secondTask.compute();
            List<ProductVO> p2 = firstTask.join();
            p1.addAll(p2);
            return p1;
        }
    }

    public static List<ProductVO> startForkJoinSum(List<ProductVO> p1,ProductRepository productRepository,ModelMapper modelMapper) {
        CreateProductTask task = new CreateProductTask(0,p1.size(),p1,productRepository,modelMapper);
        return new ForkJoinPool().invoke(task);
    }
}
