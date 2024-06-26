// Необходимо превратить собранное на семинаре дерево поиска в полноценное левостороннее красно-черное дерево. И реализовать в нем метод добавления новых элементов с балансировкой.

// Красно-черное дерево имеет следующие критерии: 
// • Каждая нода имеет цвет (красный или черный) 
// • Корень дерева всегда черный 
// • Новая нода всегда красная 
// • Красные ноды могут быть только левым ребенком 
// • У краной ноды все дети черного цвета

// Соответственно, чтобы данные условия выполнялись, после добавления элемента в дерево необходимо произвести балансировку, благодаря которой все критерии выше станут валидными. Для балансировки существует 3 операции – левый малый поворот, правый малый поворот и смена цвета.

import java.util.Scanner;

class node 
{
    node left; 
    node right;
    int data;
    boolean red = true;
    boolean black = false;
    boolean color;

    node(int data)
    {
        this.data = data;
        left = null;
        right = null;
        color = red;
    }
}

public class DZ 
{
    private static node root = null;

    node goleft(node work) 
    {
        System.out.println("поворот налево\n");
        node kid = work.right;
        node moves = work.left;
        kid.left = work;
        work.right = moves;
        return kid;
    }

    node goright(node work) 
    {
        System.out.println("поворот направо\n");
        node kid = work.left;
        node moves = work.right;
        kid.right = work;
        work.left = moves;
        return kid;
    }

    boolean redcheck(node work) 
    {
        if (work == null) 
        {
            return false;
        }
        return (work.color == true);
    }

    void paint(node a, node b) 
    {
        boolean change = a.color;
        a.color = b.color;
        b.color = change;
    }

    node insert(node wnode, int data) 
    {
        if (wnode == null) 
        {
            return new node(data);
        }
        if (data < wnode.data) 
        {
            wnode.left = insert(wnode.left, data);
        }
        else if (data > wnode.data) 
        {
            wnode.right = insert(wnode.right, data);
        }
        else 
        {
            return wnode;
        }
        if (redcheck(wnode.right) && !redcheck(wnode.left)) 
        {
            wnode = goleft(wnode);
            paint(wnode, wnode.left);
        }
        if (redcheck(wnode.left) && !redcheck(wnode.right)) 
        {
            wnode = goright(wnode);
            paint(wnode, wnode.right);
        }
        if (redcheck(wnode.right) && redcheck(wnode.left)) 
        {
            wnode.color = !wnode.color;
            wnode.left.color = false;
            wnode.right.color = false;
        }
        
        return wnode;
    }

}