//package codinggame;
//
//public class RobotsLunaires {
//
//    static class Robot {
//        Position position;
//        Orientation orientation;
//
//        public void commande(Ordre ordre) {
//            switch (ordre) {
//                case L:
//                    orientation = orientation.left();
//                    break;
//                case R:
//                    orientation = orientation.right();
//                    break;
//                case M:
//                    position = orientation.move(position);
//                    break;
//            }
//        }
//
//        @Override
//        public String toString() {
//            return position + " " + orientation;
//        }
//    }
//
//    static class Position {
//        final int x;
//        final int y;
//
//        public Position(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        @Override
//        public String toString() {
//            return x + " " + y;
//        }
//    }
//
//    enum Orientation {
//        N(p -> new Position(p.x, p.y + 1)),
//        S(p -> new Position(p.x, p.y - 1)),
//        W(p -> new Position(p.x - 1, p.y)),
//        E(p -> new Position(p.x + 1, p.y)),
//        ;
//
//        final Function<Position, Position> move;
//
//        Orientation(Function<Position, Position> move) {
//            this.move = move;
//        }
//
//        Position move(Position from) {
//            return move.apply(from);
//        }
//    }
//
//
//    enum Ordre {
//        L,
//        R,
//        M;
//    }
//
//
//    public Orientation left() {
//        switch (this) {
//            case N:
//                return W;
//            case W:
//                return S;
//            case S:
//                return E;
//            case E:
//                return N;
//        }
//        return null;
//    }
//
//    public Orientation right() {
//        switch (this) {
//            case N:
//                return E;
//            case W:
//                return N;
//            case S:
//                return W;
//            case E:
//                return S;
//        }
//        return null;
//    }
//}
