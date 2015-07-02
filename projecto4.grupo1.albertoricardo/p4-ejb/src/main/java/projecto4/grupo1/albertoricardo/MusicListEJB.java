package projecto4.grupo1.albertoricardo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.ListMusicEntities;
import pt.uc.dei.aor.paj.proj4.group1.business.ws.model.MusicDetail;

@Stateless
public class MusicListEJB implements MusicListEJBLocal {

    @PersistenceContext(name = "Playlist")
    private EntityManager em;

    @EJB
    private MusicEJBLocal crud;
    @EJB
    private UserCRUD userCrud;

    private static Logger log = LoggerFactory.getLogger(MusicListEJB.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<MusicEntity> listMusics() {
        Query q = em.createQuery("select m from MusicEntity m");
        List<MusicEntity> lme = new ArrayList<MusicEntity>();
        lme = q.getResultList();
        log.info("Consulta À base de dados para obter lista de músicas");
        return lme;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MusicEntity> listOwnMusics(UserEntity user) {
        List<MusicEntity> me = new ArrayList<>();
        Query q = em.createQuery("SELECT m FROM MusicEntity m where m.userOwner.id = :u").setParameter("u", user.getId());
        log.info("Consulta À base de dados para obter lista de músicas do utilizador " + user.getName());
        try {
            me = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("Erro ao obter lista de músicas do utilizador " + user.getName());
        }
        return me;
    }

    @Override
    public boolean update(MusicEntity music) {
        boolean success = false;
        try {
            crud.update(music);
            success = true;
            log.info("Alteração feita à música com o ID: " + music.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Erro na tentativa de alteração à música com o ID: " + music.getId());
        }

        return success;
    }

    @Override
    public boolean removerUserOwnership(UserEntity user) {
        boolean success = false;
        try {
            int complete = em.createQuery("UPDATE MusicEntity m SET m.userOwner.id = NULL where m.userOwner.id = :i").setParameter("i", user.getId())
                    .executeUpdate();
            if (complete > 0) {
                log.info("Alteração de propriedade a música");
                success = true;
            }
        } catch (Exception e) {
            log.error("Erro ao remover proprietário da música");
        }

        return success;

    }

    @Override
    public boolean removerMusicUserOwnership(MusicEntity m, UserEntity user) {
        try {
            int complete = em.createQuery("UPDATE MusicEntity m SET m.userOwner.id = NULL where m.userOwner.id = :i AND m.id = :mid")
                    .setParameter("i", user.getId()).setParameter("mid", m.getId()).executeUpdate();
            if (complete > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("Erro ao remover proprietário da música");
        }
        return false;
    }

    @Override
    public ListMusicEntities getAllMusics() {
        Query q = em.createQuery("select m from MusicEntity m");
        List<MusicEntity> lme = q.getResultList();
        if (lme != null && lme.size() > 0) {
            List<String> dozermapping = new ArrayList<>();
            dozermapping.add("META-INF/playlistmapping.xml");
            Mapper mapper = new DozerBeanMapper(dozermapping);
            ListMusicEntities lm = new ListMusicEntities();
            for (MusicEntity me : lme) {
                MusicDetail md = new MusicDetail();
                mapper.map(me, md);
                lm.getListOfMusics().add(md);
            }
            return lm;
        }
        return null;
    }

    @Override
    public ListMusicEntities getAllMusicsFromUser(int id) {
        Query q = em.createQuery("select m from MusicEntity m where m.userOwner.id = :i").setParameter("i", id);
        List<MusicEntity> lme = q.getResultList();
        if (lme != null && lme.size() > 0) {
            List<String> dozermapping = new ArrayList<>();
            dozermapping.add("META-INF/playlistmapping.xml");
            Mapper mapper = new DozerBeanMapper(dozermapping);
            ListMusicEntities lm = new ListMusicEntities();
            for (MusicEntity me : lme) {
                MusicDetail md = new MusicDetail();
                mapper.map(me, md);
                lm.getListOfMusics().add(md);
            }
            return lm;
        }
        return null;
    }

    @Override
    public MusicDetail find(int id) {
        MusicEntity me = crud.find(id);
        if (me == null)
            return null;

        List<String> dozermapping = new ArrayList<>();
        dozermapping.add("META-INF/playlistmapping.xml");
        Mapper mapper = new DozerBeanMapper(dozermapping);
        MusicDetail md = new MusicDetail();
        mapper.map(me, md);
        return md;
    }

    @Override
    public boolean removerUserOwnership(int id) {
        UserEntity user = userCrud.find(id);
        if (user == null)
            return false;

        return removerUserOwnership(user);
    }
}