package com.facufrias.bazar.service;

import com.facufrias.bazar.dto.VentaDiaDTO;
import com.facufrias.bazar.dto.VentaMayorVentaDTO;
import com.facufrias.bazar.model.Producto;
import com.facufrias.bazar.model.Venta;
import com.facufrias.bazar.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Override
    public void createVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVentaById(Long codigoVenta) {
        return ventaRepository.findById(codigoVenta).orElse(null);
    }

    @Override
    public void deleteVentaById(Long codigoVenta) {
        ventaRepository.deleteById(codigoVenta);
    }

    @Override
    public void editVentaById(Long codigoVenta, Venta ventaActualizada) {
        Venta venta = this.getVentaById(codigoVenta);
        if(venta != null){
            if(ventaActualizada.getFechaVenta() != null){
                venta.setFechaVenta(ventaActualizada.getFechaVenta());
            }
            if(ventaActualizada.getTotal() != null){
                venta.setTotal(ventaActualizada.getTotal());
            }

            if (ventaActualizada.getListaProductos() != null){
                venta.setListaProductos(ventaActualizada.getListaProductos());
            }
            if(ventaActualizada.getUnCliente() != null){
                venta.setUnCliente(ventaActualizada.getUnCliente());
            }
            ventaRepository.save(venta);
        }
    }

    @Override
    public List<Producto> getProductosByVenta(Long codigoVenta) {
        Venta venta = this.getVentaById(codigoVenta);
        List<Producto> listaProductos = new ArrayList<>();
        if(venta != null && venta.getListaProductos() != null){
                listaProductos.addAll(venta.getListaProductos());
        }
        return listaProductos;

        // Otra forma de hacerlo (directa)
    /*
    Venta venta = this.getVentaById(codigoVenta);
    if (venta != null && venta.getListaProductos() != null) {
        return venta.getListaProductos();
    }
    return new ArrayList<>();
    */
    }

    @Override
    public List<Venta> getVentasByFecha(LocalDate fechaVenta) {
        List<Venta> listaVentas = this.getVentas();

        return listaVentas.stream().filter(venta -> venta.getFechaVenta() != null && venta.getFechaVenta().equals(fechaVenta)).toList();

        /*for(Venta venta : listaVentas){
                if(venta.getFechaVenta() != null &&venta.getFechaVenta().equals(fechaVenta)){
                    listaFechaVenta.add(venta);
            }
        }*/

    }

    @Override
    public VentaDiaDTO getSumAndCountVentasByFecha(LocalDate fechaVenta) {
        List<Venta> listaVentas = this.getVentasByFecha(fechaVenta);
        VentaDiaDTO ventaDiaDTO = new VentaDiaDTO();
        double totalMonto = listaVentas.stream().filter(venta -> venta.getTotal() != null).mapToDouble(Venta::getTotal).sum();
        ventaDiaDTO.setMontoTotal(totalMonto);
        ventaDiaDTO.setCantidadVentas(listaVentas.size());
        return ventaDiaDTO;
        /*
        double totalMonto = 0;
        for(Venta venta : listaVentas){
            if(venta.getTotal() != null){
                totalMonto += venta.getTotal();
            }
        }
        ventaDiaDTO.setMontoTotal(totalMonto);
        ventaDiaDTO.setCantidadVentas(listaVentas.size());
        return  ventaDiaDTO;*/
    }

    @Override
    public VentaMayorVentaDTO getMayorVenta() {
        List<Venta> listaVentas = this.getVentas();
        VentaMayorVentaDTO ventaMayorVentaDTO = new VentaMayorVentaDTO();
        double maxMonto = 0;

        Venta ventaMayor = listaVentas.stream().filter(venta -> venta.getTotal() != null).max(Comparator.comparing(Venta::getTotal)).orElse(null);

        /*for(Venta venta : listaVentas){
            if(venta.getTotal() != null){
                if(venta.getTotal() > maxMonto){
                    maxMonto = venta.getTotal();
                    ventaMayor = venta;


                }
            }*/

        if(ventaMayor != null){
            ventaMayorVentaDTO.setCodigoVenta(ventaMayor.getCodigoVenta());
            ventaMayorVentaDTO.setTotal(ventaMayor.getTotal());
            if(ventaMayor.getUnCliente() != null){
                ventaMayorVentaDTO.setNombreCliente(ventaMayor.getUnCliente().getNombre());
                ventaMayorVentaDTO.setApellidoCliente(ventaMayor.getUnCliente().getApellido());
            }
            if(ventaMayor.getListaProductos() != null){
                ventaMayorVentaDTO.setCantidadProductos(ventaMayor.getListaProductos().size());
            }
        }

        return ventaMayorVentaDTO;
    }
}
