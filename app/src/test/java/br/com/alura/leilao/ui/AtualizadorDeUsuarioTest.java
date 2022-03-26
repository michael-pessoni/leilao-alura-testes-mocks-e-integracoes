package br.com.alura.leilao.ui;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deve_AtualizarUsuario_QuandoSalvarUsuario(){
        AtualizadorDeUsuario atualizador = new AtualizadorDeUsuario(dao,
                adapter,
                recyclerView);

        Usuario michael = new Usuario("Michael");
        when(dao.salva(michael)).thenReturn(new Usuario(1,"Michael"));
        when(adapter.getItemCount()).thenReturn(1);
        atualizador.salva(michael);

        verify(dao).salva(new Usuario("Michael"));
        verify(adapter).adiciona(new Usuario(1,"Michael"));
        verify(recyclerView).smoothScrollToPosition(0);
    }

}